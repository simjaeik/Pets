function warnEmpty(){
    alert("댓글을 입력해주세요!");
}
function dateToString(date) {

    const dateString = date.toISOString();
    const dateToString = dateString.substring(0,10) + " " + dateString.substring(11,19);
    return dateToString;
}
function submitComment(){
    const newcommentEL = document.getElementById("new-comment");
    const newcomment = newcommentEL.value.trim(); //trim() : 공백제거

    if(newcomment){

        const dateEL = document.createElement('div');
        dateEL.classList.add("comment-date");
        const dateString = dateToString(new Date());
        dateEL.innerText = dateString;

        const contentEL = document.createElement('div');
        contentEL.classList.add('coment-content');
        contentEL.innerText = newcomment;
  
        const commentEL = document.createElement('div');
        commentEL.classList.add('comment-content');
        commentEL.appendChild(contentEL);
        commentEL.appendChild(dateEL);
       

        document.getElementById('comments').appendChild(commentEL);
        newcommentEL.value="";     

        const countEL = document.getElementById('comments-count');
        const count = countEL.innerText;
        countEL.innerText = parseInt(count) + 1;

    } else warnEmpty();
}