package com.corkercode.geek.common.enums;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 通用异常枚举
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/12 23:05
 */
public enum ErrorCodeEnum implements IEnum {

    /*
      SC_ACCEPTED 状态码(202)表明,一个请求被处理,但未能完成。
      SC_BAD_GATEWAY 状态码(502)表明HTTP服务器收到了一个无效回应从服务器提供咨询当充当代理或网关
      SC_BAD_REQUEST 状态码(400)指示客户端发送的请求是语法有误。
      SC_CONFLICT 状态码(409),指示请求不能完成由于冲突与资源的当前状态
      SC_CONTINUE 状态码(100)指示客户端可以继续
      SC_CREATED 状态码(201)指示请求成功,创造了一个新的服务器上的资源。
      SC_EXPECTATION_FAILED  状态码(417),指示服务器无法满足预期给定的期望请求头
      SC_FORBIDDEN 状态码(403)指示服务器理解的请求,但拒绝履行它。
      SC_FOUND  状态码(302),指示资源驻留暂时在一个不同的URI。
      SC_GATEWAY_TIMEOUT 状态码(504),指示服务器没有得到及时的响应,而代理从上游服务器作为网关或代理。
      SC_GONE 状态码(410),指示资源不再是可用的服务器,没有转发地址是已知的。
      SC_HTTP_VERSION_NOT_SUPPORTED 状态码(505),指示服务器不支持或拒绝支持HTTP协议版本,用于请求消息。
      SC_INTERNAL_SERVER_ERROR  状态码(500)表明一个错误在HTTP服务器,该情况阻止了完成请求。
      SC_LENGTH_REQUIRED 状态码(411),指示请求不能处理没有定义内容长度。
      SC_METHOD_NOT_ALLOWED 状态码(405)表明该方法中指定的请求线是不允许资源识别由请求uri所指定资源。
      SC_MOVED_PERMANENTLY 状态码(301),指示资源已经永久搬到一个新的位置,未来的引用应该使用一个新的URI请求。
      SC_MOVED_TEMPORARILY 状态码(302),指示资源暂时搬到另一个位置,但是,未来的引用应该仍然使用原来的URI来访问资源。
      SC_MULTIPLE_CHOICES 状态码(300),指示请求的资源对应的任何一个一组表示,每个都有自己的具体位置。
      SC_NO_CONTENT 状态码(204),指示请求成功了但是没有新信息返回。
      SC_NON_AUTHORITATIVE_INFORMATION  状态码(203),指示客户端提出的元信息不是来自服务器。
      SC_NOT_ACCEPTABLE 状态码(406)表明该资源被请求只能生成响应实体具有内容特征不接受根据accept头信息发送的请求。
      SC_NOT_FOUND 状态码(404),指示请求的资源不可用。
      SC_NOT_IMPLEMENTED  状态码(501)显示HTTP服务器不支持这个功能需要满足的要求。
      SC_NOT_MODIFIED 状态码(304)表明一个条件GET操作发现,资源是可用的,而不是修改。
      SC_OK 状态码(200)指示请求成功了一般。
      SC_PARTIAL_CONTENT 状态码(206)说明服务器已经完成了部分GET请求的资源。
      SC_PAYMENT_REQUIRED 状态码(402)保留以供将来使用。
      SC_PRECONDITION_FAILED  状态码(412)表明预处理在一个或多个请求头字段计算为false时在服务器上测试。
      SC_PROXY_AUTHENTICATION_REQUIRED 状态码(407),指示客户机必须首先验证本身与代理。
      SC_REQUEST_ENTITY_TOO_LARGE 状态码(413),指示服务器拒绝处理请求,因为请求实体大于服务器是否愿意或者能够处理。
      SC_REQUEST_TIMEOUT 状态码(408),指示客户端没有产生一个请求的时间内服务器准备等。
      SC_REQUEST_URI_TOO_LONG 状态码(414),指示服务器拒绝服务请求,因为要求通用长于服务器愿意解释。
      SC_REQUESTED_RANGE_NOT_SATISFIABLE 状态码(416),指示服务器不能提供所请求的字节范围。
      SC_RESET_CONTENT 状态码(205),指示代理人应当重置文档视图导致发送的请求。
      SC_SEE_OTHER 状态码(303)表明此请求的响应可以被发现在一个不同的URI。
      SC_SERVICE_UNAVAILABLE 状态码(503)表明HTTP服务器暂时超载,无法处理请求。
      SC_SWITCHING_PROTOCOLS 状态码(101)指示服务器切换协议根据升级标题
      SC_TEMPORARY_REDIRECT 状态码(307),指示请求的资源驻留暂时在一个不同的URI
      SC_UNAUTHORIZED 状态码(401),指示请求需要HTTP身份验证。
      SC_UNSUPPORTED_MEDIA_TYPE 状态码(415),指示服务器拒绝服务请求,因为实体请求的格式不支持请求的资源请求的方法。
      SC_USE_PROXY 状态码(305),指示请求的资源必须通过代理Locationfield给出的。
     */

    /**
     * 400
     */
    BAD_REQUEST(HttpServletResponse.SC_BAD_REQUEST, "请求参数错误或不完整"),
    /**
     * 401
     */
    UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "请先进行认证"),
    /**
     * 403
     */
    FORBIDDEN(HttpServletResponse.SC_FORBIDDEN, "无权查看"),
    /**
     * 404
     */
    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "未找到该路径"),
    /**
     * 405
     */
    METHOD_NOT_ALLOWED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "请求方式不支持"),
    /**
     * 406
     */
    NOT_ACCEPTABLE(HttpServletResponse.SC_NOT_ACCEPTABLE, "不可接受该请求"),
    /**
     * 411
     */
    LENGTH_REQUIRED(HttpServletResponse.SC_LENGTH_REQUIRED, "长度受限制"),
    /**
     * 415
     */
    UNSUPPORTED_MEDIA_TYPE(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "不支持的媒体类型"),
    /**
     * 416
     */
    REQUESTED_RANGE_NOT_SATISFIABLE(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE, "不能满足请求的范围"),
    /**
     * 500
     */
    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器正在升级，请耐心等待"),
    /**
     * 503
     */
    SERVICE_UNAVAILABLE(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "请求超时"),
    /**
     * JSON格式错误
     */
    JSON_FORMAT_ERROR(HttpServletResponse.SC_BAD_REQUEST, "JSON格式错误"),
    /**
     * 演示系统，无法该操作
     */
    DEMO_SYSTEM_CANNOT_DO(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "演示系统，无法该操作"),

    ;

    private final int httpCode;
    private final String msg;

    ErrorCodeEnum(int httpCode, String msg) {
        this.httpCode = httpCode;
        this.msg = msg;
    }

    public int httpCode() {
        return this.httpCode;
    }

    public String msg() {
        return this.msg;
    }

}
