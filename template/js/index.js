$(function(){
    let path = window.location.pathname;
    let index = path.lastIndexOf("/"); 
    path = path.substring(index+1);
    if(path.trim()=="") {
        path="index.html";
    }
    
    $($("#navbar [href='"+path+"']").parent()).attr({"class":"active"});
});