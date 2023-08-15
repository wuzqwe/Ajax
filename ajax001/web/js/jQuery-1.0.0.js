function jQuery(selector){
    if (typeof selector=="string"){
        if (selector.charAt(0)=="#"){
            domObj = document.getElementById(selector.substring(1));
            return  new jQuery()
        }
    }
    if(typeof selector=="function"){
        window.onload=selector
    }
    this.html=function (htmlStr){
        domObj.innerHTML=htmlStr
    }
    this.click=function (fun){
        domObj.onclick=fun
    }
    this.focus=function (fun){
        domObj.onfocus=fun
    }
    this.blur=function (fun){
        domObj.onblur=fun
    }
    this.change=function (fun){
        domObj.onchange=fun
    }

    this.val=function (v){
        if (v == undefined) {
            return domObj.value
        }else {
            domObj.value=v
        }
    }
    this.keyup=function (fun){
        domObj.onkeyup=fun
    }
    /**
     * 分析：使用ajax函数发送ajax请求的时候，需要程序员给我们传过来什么
     *    请求方式（type）:GET/POST
     *    请求的URL(url)：url
     *    请求时提交的数据（data）：data
     *    请求时发送异步请求还是同步请求（async）:true表示异步，false表示同步
     */
    jQuery.ajax=function (jsonArgs){
        var xhr=new XMLHttpRequest()
        xhr.onreadystatechange=function (){
            if (this.readyState==4){
                if (this.status==200){
                    var jsonObj=JSON.parse(this.responseText)
                    jsonArgs.success(jsonObj)
                }else {
                    alert(this.status)
                }
            }
        }
        if (jsonArgs.type.toUpperCase()=="POST"){
            xhr.open("POST",jsonArgs.url,jsonArgs.async)
            xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded")
            xhr.send(jsonArgs.data)
        }
        if (jsonArgs.type.toUpperCase()=="GET"){
            xhr.open("GET",jsonArgs.url+"?"+jsonArgs.data,jsonArgs.async)
            xhr.send()
        }
    }
}
$=jQuery
new jQuery()