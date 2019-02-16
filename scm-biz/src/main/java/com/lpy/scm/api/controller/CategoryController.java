package com.lpy.scm.api.controller;

import com.lpy.scm.bean.ApiResponse;
import com.lpy.scm.dto.CategoryDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.AddCategoryParam;
import com.lpy.scm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lpy
 * @date 2019/2/15 16:21
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("list")
    @ResponseBody
    public ApiResponse<List<CategoryDTO>> getCategory() throws ParamException {
        return ApiResponse.<List<CategoryDTO>>success().setData(categoryService.getCategory());
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse addCategory(AddCategoryParam addCategoryParam) throws ParamException {
        categoryService.addCategory(addCategoryParam);
        return ApiResponse.success().setMsg("添加成功");
    }

    @RequestMapping("{id}")
    @ResponseBody
    public ApiResponse<CategoryDTO> queryCategoryById(@PathVariable(value = "id") String id) throws ParamException {
        return ApiResponse.<CategoryDTO>success().setData(categoryService.queryCategoryById(id));
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse editCategory(AddCategoryParam addCategoryParam) throws ParamException {
        System.out.println(addCategoryParam.toString());
        categoryService.editCategory(addCategoryParam);
        return ApiResponse.<List<CategoryDTO>>success().setMsg("修改成功");
    }
}
