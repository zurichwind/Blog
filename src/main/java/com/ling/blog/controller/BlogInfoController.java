package com.ling.blog.controller;

import com.ling.blog.annotation.OptLog;
import com.ling.blog.constant.OptTypeConst;
import com.ling.blog.dto.BlogBackInfoDTO;
import com.ling.blog.dto.BlogHomeInfoDTO;
import com.ling.blog.enums.FilePathEnum;
import com.ling.blog.service.BlogInfoService;
import com.ling.blog.strategy.context.UploadStrategyContext;
import com.ling.blog.vo.BlogInfoVO;
import com.ling.blog.vo.Result;
import com.ling.blog.vo.WebsiteConfigVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA.
 * 博客信息控制器
 *
 * @Author : 风间离
 * @create 2023/5/29 9:38
 */
@Api(tags = "博客信息模块")
@RestController
public class BlogInfoController {
    @Autowired
    private BlogInfoService blogInfoService;
    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    /**
     * 查看博客信息
     *
     * @return {@link Result <BlogHomeInfoDTO>} 博客信息
     */
    @ApiOperation(value = "查看博客信息")
    @GetMapping("/")
    public Result<BlogHomeInfoDTO> getBlogHomeInfo() {
        return Result.ok(blogInfoService.getBlogHomeInfo());
    }

    /**
     * 查看后台信息
     *
     * @return {@link Result<BlogBackInfoDTO>} 后台信息
     */
    @ApiOperation(value = "查看后台信息")
    @GetMapping("/admin")
    public Result<BlogBackInfoDTO> getBlogBackInfo() {
        return Result.ok(blogInfoService.getBlogBackInfo());
    }

    /**
     * 上传博客配置图片
     *
     * @param file 文件
     * @return {@link Result<String>} 博客配置图片
     */
    @ApiOperation(value = "上传博客配置图片")
    @ApiImplicitParam(name = "file", value = "图片", required = true, dataType = "MultipartFile")
    @PostMapping("/admin/config/images")
    public Result<String> savePhotoAlbumCover(MultipartFile file) {
        return Result.ok(uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.CONFIG.getPath()));
    }

    /**
     * 上传图床图片
     *
     * @param file 文件
     * @return {@link Result<String>} 图床图片
     */
    @ApiOperation(value = "图床上传")
    @ApiImplicitParam(name = "file", value = "图片", required = true, dataType = "MultipartFile")
    @PostMapping("/admin/cloud/images")
    public Result<String> uploadCloudImage(MultipartFile file) {
        return Result.ok(uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.CLOUD.getPath()));
    }

    /**
     * 更新网站配置
     *
     * @param websiteConfigVO 网站配置信息
     * @return {@link Result}
     */
    @ApiOperation(value = "更新网站配置")
    @PutMapping("/admin/website/config")
    public Result<?> updateWebsiteConfig(@Valid @RequestBody WebsiteConfigVO websiteConfigVO) {
        blogInfoService.updateWebsiteConfig(websiteConfigVO);
        return Result.ok();
    }

    /**
     * 获取网站配置
     *
     * @return {@link Result<WebsiteConfigVO>} 网站配置
     */
    @ApiOperation(value = "获取网站配置")
    @GetMapping("/admin/website/config")
    public Result<WebsiteConfigVO> getWebsiteConfig() {
        return Result.ok(blogInfoService.getWebsiteConfig());
    }

    /**
     * 查看关于我信息
     *
     * @return {@link Result<String>} 关于我信息
     */
    @ApiOperation(value = "查看关于我信息")
    @GetMapping("/about")
    public Result<String> getAbout() {
        return Result.ok(blogInfoService.getAbout());
    }

    /**
     * 修改关于我信息
     *
     * @param blogInfoVO 博客信息
     * @return {@link Result<>}
     */
    @OptLog(optType = OptTypeConst.UPDATE)
    @ApiOperation(value = "修改关于我信息")
    @PutMapping("/admin/about")
    public Result<?> updateAbout(@Valid @RequestBody BlogInfoVO blogInfoVO) {
        blogInfoService.updateAbout(blogInfoVO);
        return Result.ok();
    }

    /**
     * 上传访客信息
     *
     * @return {@link Result}
     */
    @PostMapping("/report")
    public Result<?> report() {
        blogInfoService.report();
        return Result.ok();
    }

}

