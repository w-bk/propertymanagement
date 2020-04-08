package org.wbk.propertymanagement.response;

/**
 * @Description: 用于与前端传值
 * @Author 王宝凯
 * @Date 2020/4/4
 */
public class ServerResponse {

    /**
     * @Description 用于前端判断成功或失败
     * @Author 王宝凯
     * @Date 2020/4/4
     **/
    private boolean success;
    /**
     * @Description 消息信息显示
     * @Author 王宝凯
     * @Date 2020/4/4
     **/
    private String msg;
    /**
     * @Description 返回数据
     * @Author 王宝凯
     * @Date 2020/4/4
     **/
    private Object data;

    public ServerResponse(boolean success , Object data){
        this.success = success;
        this.data = data;
    }

    public ServerResponse(boolean success , String msg){
        this.success = success;
        this.msg = msg;
    }

    public ServerResponse(boolean success , String msg, Object data){
        this.success = success;
        this.msg = msg;
        this.data = data;
    }
    /**
     * @Description //消息返回类
     * @Author 王宝凯
     * @Date 2020/4/4
     **/
    public static ServerResponse sendSuccess(){
        return new ServerResponse(true,"操作成功");
    }
    /**
     * @Description //自定义消息返回类
     * @Author 王宝凯
     * @Date 2020/4/4
     **/
    public static ServerResponse sendSuccess(String msg){
        return new ServerResponse(true,msg);
    }
    /**
     * @Description //自定义消息返回类
     * @Author 王宝凯
     * @Date 2020/4/4
     **/
    public static ServerResponse sendSuccess(Object data){
        return new ServerResponse(true,data);
    }
    /**
     * @Description //自定义消息和数据返回类
     * @Author 王宝凯
     * @Date 2020/4/4
     **/
    public static ServerResponse sendSuccess(String msg, Object data){
        return new ServerResponse(true,msg,data);
    }
    /**
     * @Description //消息返回类
     * @Author 王宝凯
     * @Date 2020/4/4
     **/
    public static ServerResponse sendError(){
        return new ServerResponse(false,"操作失败");
    }
    /**
     * @Description //自定义消息返回类
     * @Author 王宝凯
     * @Date 2020/4/4
     **/
    public static ServerResponse sendError(String msg){
        return new ServerResponse(false,msg);
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
