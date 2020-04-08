package org.wbk.propertymanagement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.wbk.propertymanagement.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王宝凯
 * @since 2020-01-12
 */
public interface INoticeService extends IService<Notice> {

    /**
     * @Description 查询全部公告和公告的全部信息
     * @Author 王宝凯
     * @Date 2020/3/1
     **/
    List<Notice> noticeList();

    /**
     * @Description 查询全部公告并分页及搜索
     * @Author 王宝凯
     * @Date 2020/3/13
     **/
    IPage<Notice> noticePageList(Integer page, Integer limit,String noticeTitle);

    /**
     * @Description 公告信息的删除
     * @Author 王宝凯
     * @Date 2020/3/13
     **/
    int deleteNotice(int id);

    /**
     * @Description 实现编辑页面的弹窗和数据的显示
     * @Author 王宝凯
     * @Date 2020/3/14
     **/
    Notice selectByIdNotice(int id);

    /**
     * @Description 实现编辑页面的修改
     * @Author 王宝凯
     * @Date 2020/3/14
     **/
    int updateNotice(Notice updateNotice);

    /**
     * @Description 实现添加页面
     * @Author 王宝凯
     * @Date 2020/3/14
     **/
    int insertNotice(Notice insertNotice);
}
