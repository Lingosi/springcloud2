package org.servicea.util;


public class Response {
 protected int code;
 protected String msg;

 private Response(int code, String msg) {
     this.code = code;
     this.msg = msg;
 }

 public static Response response(int code) {
     return new Response(code, "");
 }

 public static Response response(int code, String msg) {
     return new Response(code, msg);
 }

 public static <E> Response.DataResponse<E> response(int code, String msg, E data) {
     return new Response.DataResponse(code, msg, data);
 }

 public static Response success() {
     return new Response(200, "common.success");
 }

 public static <E> Response.DataResponse<E> success(E data) {
     return new Response.DataResponse(200, "common.success", data);
 }

 public static <E> Response.DataResponse<E> successPage(MyPage page) {
     return new Response.PageResponse(200, "common.fail", page);
 }

 public static Response error() {
     return new Response(500, "common.fail");
 }

 public static Response error(String msg) {
     return new Response(500, msg);
 }

 public static <E> Response.DataResponse<E> error(E data) {
     return new Response.DataResponse(500, "common.fail", data);
 }

 public int getCode() {
     return this.code;
 }

 public String getMsg() {
     return this.msg;
 }

 public static class PageResponse<T> extends Response.DataResponse<T> {
     Integer total;
     Integer page;
     Integer pageSize;
     Integer resultCount;

     public PageResponse(int code, String msg, MyPage mypage) {
         super(code, msg, (T)mypage.getData());
         this.total = mypage.total;
         this.total = mypage.total;
         this.page = mypage.getStart();
         this.pageSize = mypage.getPageSize();
         this.resultCount = mypage.resultCount;
     }

     public Integer getTotal() {
         return this.total;
     }

     public Integer getPage() {
         return this.page;
     }

     public Integer getPageSize() {
         return this.pageSize;
     }

     public Integer getResultCount() {
         return this.resultCount;
     }
 }

 public static class DataResponse<T> extends Response {
     T data;

     public DataResponse(int code, String msg, T data) {
         super(code, msg);
         this.data = data;
     }

     public T getData() {
         return this.data;
     }
 }
}

