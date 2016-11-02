package com.xy.xyblog.controller;

import com.xy.xyblog.bean.Blog;
import com.xy.xyblog.bean.Tag;
import com.xy.xyblog.bean.TagRelation;
import com.xy.xyblog.bean.ext.BlogExt;
import com.xy.xyblog.bean.ext.TagExt;
import com.xy.xyblog.bean.inbean.BlogInsertIn;
import com.xy.xyblog.bean.qbean.BlogQBean;
import com.xy.xyblog.constant.TagConstant;
import com.xy.xyblog.service.blog.BlogService;
import com.xy.xyblog.service.tag.TagService;
import com.xy.xyblog.util.ParamUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 博客文章
 *
 * @author alex
 */

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) throws UnsupportedEncodingException, InvocationTargetException, IllegalAccessException {
        return getBlogList(request);
    }
        @RequestMapping(value = "/blog/list", method = RequestMethod.GET)
    public ModelAndView getBlogList(HttpServletRequest request) throws UnsupportedEncodingException, InvocationTargetException, IllegalAccessException {
        request.setCharacterEncoding("utf-8");
        BlogQBean qbean = new BlogQBean();
        qbean.setOrder("create_time");
        qbean.setSort("desc");
        String title = request.getParameter("title");
        if(!StringUtils.isEmpty(title)){
            qbean.setTitle(title);
        }
        String pageNum = request.getParameter("pageNum");
        if(!StringUtils.isEmpty(pageNum)){
            qbean.setPageNum(Integer.parseInt(pageNum));
        }
        String tagId = request.getParameter("tagId");
        if(!StringUtils.isEmpty(tagId)){
            int tagidi=Integer.parseInt(tagId);
            qbean.setTagId(tagidi);
        }
        Map<String,Object> result = new HashMap<String,Object>();
        List<Blog> blogs = blogService.getBlogListBySearchIn(qbean);
        List<BlogExt> blogExts = new ArrayList<BlogExt>();
        for(Blog b : blogs){
            BlogExt ext = new BlogExt();
            BeanUtils.copyProperties(ext,b);
            ext.setTagList(getTagsByIds(b.getTags()));
            blogExts.add(ext);
        }
        result.put("blogList", blogExts);
        result.put("blogCount", blogService.countBlogListBySearchIn(qbean));
        result.put("pageNum", qbean.getPageNum());
        result.put("tagId", qbean.getTagId());
        result.put("title", qbean.getTitle());
        //标签
        List<Tag> tags = tagService.getTagByType(TagConstant.TAG_TYPE_BLOG);
        List<TagExt> blogTagList = new ArrayList<TagExt>();
        for(Tag tag : tags){
            TagExt ext = new TagExt();
            BeanUtils.copyProperties(ext,tag);
            ext.setBlogCount(tagService.countRelationByType(tag.getId(), TagConstant.TAGRELATION_BLOG));
            blogTagList.add(ext);
        }
        result.put("blogTagList", blogTagList);
        return new ModelAndView("index",result);
    }
    public List<Tag> getTagsByIds(String tagIds){
        List<Tag> allTags = tagService.getTagByType(TagConstant.TAG_TYPE_BLOG);
        List<Tag> result = new ArrayList<Tag>();
        for(String t : tagIds.split(",")){
            for(Tag tt : allTags){
                if(Integer.parseInt(t) == tt.getId()){
                    result.add(tt);
                }
            }
        }
        return result;
    }
    @RequestMapping(value = "/blog/md", method = RequestMethod.GET)
    public ModelAndView blogMd(HttpServletRequest request) {
        return new ModelAndView("md");
    }
    @RequestMapping(value = "/webaudio/audio", method = RequestMethod.GET)
    public ModelAndView webaudio(HttpServletRequest request) {
        return new ModelAndView("/webaudio/audio");
    }
    @RequestMapping(value = "/blog/content/{blogId}", method = RequestMethod.GET)
    public ModelAndView blogDetail(@PathVariable("blogId") Long blogId, HttpServletRequest request) throws InvocationTargetException, IllegalAccessException {
        Map<String,Object> result = new HashMap<String,Object>();
        List<Tag> tags = tagService.getTagByType(TagConstant.TAG_TYPE_BLOG);
        List<TagExt> blogTagList = new ArrayList<TagExt>();
        for(Tag tag : tags){
            TagExt ext = new TagExt();
            BeanUtils.copyProperties(ext,tag);
            ext.setBlogCount(tagService.countRelationByType(tag.getId(), TagConstant.TAGRELATION_BLOG));
            blogTagList.add(ext);
        }
        result.put("blogTagList", blogTagList);
        result.put("blogContent",blogService.getBlogById(blogId));
        return new ModelAndView("article_content",result);
    }
    @RequestMapping(value = "/blog/insert", method = RequestMethod.POST)
    public ModelAndView insertBlog(RedirectAttributes redirectAttributes, HttpServletRequest request) {
        Blog blog = new Blog();
        BlogInsertIn in=new BlogInsertIn();
        try {
            in = ParamUtil.toBean(request, BlogInsertIn.class);
            
            if(StringUtils.isEmpty(in.getTitle())){
                redirectAttributes.addFlashAttribute("insertResult", "标题不能为空！");
                return new ModelAndView("redirect:/blog/md");
            }
            if(StringUtils.isEmpty(in.getContent())){
                redirectAttributes.addFlashAttribute("insertResult", "内容不能为空！");
                return new ModelAndView("redirect:/blog/md");
            }
            if(StringUtils.isEmpty(in.getTags())){
                redirectAttributes.addFlashAttribute("insertResult", "标签不能为空！");
                return new ModelAndView("redirect:/blog/md");
            }
            blog.setTitle(in.getTitle());
            blog.setContent(in.getContent());
            blog.setClassify(1);
            blog.setTags(parseTagId(in.getTags().toLowerCase()));
            blog.setCreateTime(new Date());
            String listContent = in.getListContent().length()<255?in.getListContent():in.getListContent().substring(0, 255);
            blog.setListContent(filtHtml(listContent));
             long id = blogService.insert(blog);
            if(id>0){
                redirectAttributes.addFlashAttribute("insertResult", "保存成功！");
                //标签保存
                List<TagRelation> tagRelations = parseTag(id,in.getTags());
                if(CollectionUtils.isEmpty(tagRelations)){
                    tagRelations.add(new TagRelation(TagConstant.TAGRELATION_BLOG,id,TagConstant.TAG_QITA_ID));//其他
                }
                int rcount = tagService.insertTagRelation(tagRelations);
                if(rcount<=0){
                    System.out.println("保存标签关系失败");
                }
            }else{
                redirectAttributes.addFlashAttribute("insertResult", "保存失败！");
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ModelAndView("redirect:/blog/list");
    }
    /**
     * 匹配用户输入的标签，生成标签关系
     * @param relId
     * @param tags
     * @return 
     */
    public List<TagRelation> parseTag(long relId,String tags){
        List<Tag> allTags = tagService.getTagByType(TagConstant.TAG_TYPE_BLOG);
        List<TagRelation> relations = new ArrayList<TagRelation>();
        for(Tag tag : allTags){
            if(tags.contains(tag.getKeyword())){
                relations.add(new TagRelation(TagConstant.TAGRELATION_BLOG,relId,tag.getId()));
            }
        }
        return relations;
    }
    /**
     * 解析保存的标签ID，用逗号分隔，保存到博客记录tags中
     */
    public String parseTagId(String tags){
        List<Tag> allTags = tagService.getTagByType(TagConstant.TAG_TYPE_BLOG);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<allTags.size();i++){
            Tag tag = allTags.get(i);
            if(tags.contains(tag.getKeyword())){
                if(!StringUtils.isEmpty(sb.toString())) sb.append(",");
                sb.append(tag.getId());
            }
        }
        if(StringUtils.isEmpty(sb.toString())){
            sb.append(TagConstant.TAG_QITA_ID);
        }
        return sb.toString();
    }
    public static String filtHtml(String s) {
		if (!s.equals("")) {
			String str = s.replaceAll("<[.[^<]]*>", "");
			return str;
		} else {
			return s;
		}
	}
}
