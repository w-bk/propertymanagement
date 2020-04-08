package org.wbk.propertymanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.wbk.propertymanagement.entity.Notice;
import org.wbk.propertymanagement.mapper.NoticeMapper;
import org.wbk.propertymanagement.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王宝凯
 * @since 2020-01-12
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * @Description 查询全部公告和公告的全部信息
     * @Author 王宝凯
     * @Date 2020/3/1
     **/
    @Override
    public List<Notice> noticeList() {
        QueryWrapper<Notice> noticeQueryWrapperList = new QueryWrapper<>();
        noticeQueryWrapperList.orderByDesc("update_time");
        return noticeMapper.selectList(noticeQueryWrapperList);
        //return noticeMapper.selectList(null);
    }

    /**
     * @Description 查询全部公告并分页及搜索 按数据库的更新时间进行降序
     * @Author 王宝凯
     * @Date 2020/3/13
     **/
    @Override
    public IPage<Notice> noticePageList(Integer page, Integer limit, String noticeTitle) {
        QueryWrapper<Notice> noticeQueryWrapper = new QueryWrapper<>();
        noticeQueryWrapper.like(!("").equals(noticeTitle) && noticeTitle !=null,"notice_title",noticeTitle).orderByDesc("update_time");
        return noticeMapper.selectPage(new Page<>(page,limit),noticeQueryWrapper);
    }

    /**
     * @Description 公告信息的删除
     * @Author 王宝凯
     * @Date 2020/3/13
     **/
    @Override
    public int deleteNotice(int id) {
        return noticeMapper.deleteById(id);
    }

    /**
     * @Description 实现编辑页面的弹窗和数据的显示
     * @Author 王宝凯
     * @Date 2020/3/14
     **/
    @Override
    public Notice selectByIdNotice(int id) {
        return noticeMapper.selectById(id);
    }

    /**
     * @Description 实现编辑页面的修改
     * @Author 王宝凯
     * @Date 2020/3/14
     **/
    @Override
    public int updateNotice(Notice updateNotice) {
        return noticeMapper.updateById(updateNotice);
    }

    /**
     * @Description 实现添加页面
     * @Author 王宝凯
     * @Date 2020/3/14
     **/
    @Override
    public int insertNotice(Notice insertNotice) {
        return noticeMapper.insert(insertNotice);
    }
}
