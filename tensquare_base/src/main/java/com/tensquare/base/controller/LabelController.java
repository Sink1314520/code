package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;

/**
 * 基础微服务 - 控制层
 */
@RestController
@RequestMapping("/label")
@CrossOrigin //解决前后台调用的跨域问题
public class LabelController {

    @Autowired
    private LabelService baseService;

    /**
     * 增
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){
        baseService.save(label);
        return  new Result(true, StatusCode.OK,"增加标签成功");
    }


    /**
     * 删除
     */
    @RequestMapping(value = "/{labelId}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String labelId){
        baseService.deleteById(labelId);
        return  new Result(true, StatusCode.OK,"删除标签成功");
    }


    /**
     * 改
     */
    @RequestMapping(value = "/{labelId}",method = RequestMethod.PUT)
    public Result updateById(@RequestBody Label label,@PathVariable String labelId){
        baseService.updateById(label,labelId);
        return  new Result(true, StatusCode.OK,"更新标签成功");
    }


    /**
     * 查询所有
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Label> labelList =  baseService.findAll();
        return  new Result(true, StatusCode.OK,"查询所有标签成功",labelList);
    }


    /**
     * 根据id查询
     */
    @RequestMapping(value = "/{labelId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String  labelId){
        //int rs = 1/0;
        Label label = baseService.findById(labelId);
        return  new Result(true, StatusCode.OK,"查询单个标签成功",label);
    }


    /**
     * /label/search  标签分页
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result search(@RequestBody Map map){
        //int rs = 1/0;
        List<Label> labelList = baseService.search(map);
        return  new Result(true, StatusCode.OK,"根据条件查询标签信息成功",labelList);
    }

    /**
     * 条件分页查询
     * @param page
     * @param size
     * @param map
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result searchByPage(@PathVariable int page,@PathVariable int size,@RequestBody Map map){
        //int rs = 1/0;
        Page<Label> labelPage = baseService.searchByPage(map,page,size);
        return  new Result(true, StatusCode.OK,"根据条件分页查询标签信息成功",new PageResult<>(labelPage.getTotalElements(),labelPage.getContent()));
    }


    @Value("${sms.ip}")
    private String ip;

    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    public String ip() {
            return ip;
        }



}
