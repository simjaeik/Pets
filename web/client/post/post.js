//제목, 닉네임, 내용이 비어있으면 화면이동이 안되게 한다
function check_blank(){

    let radiobtn = document.getElementsByName("chk_info");
    let checktitle = document.getElementById("posttitle");
    let checkname = document.getElementById("writername");
    let checkcontent = document.getElementById("content");

    let cnt = 0, check = false;
    for (let i=0;i<radiobtn.length;i++){
        
        if(radiobtn[i].checked === false) { cnt++; }
        
        if ( cnt === radiobtn.length) { check = false; }
        else { check = true; } 
    }

    if ( check === false) { alert("게시물을 선택해주세요. "); }
    else if ( checktitle.value === "") { alert(" 제목이 입력되지 않았습니다. "); }
    else if ( checkname.value === "") { alert(" 닉네임이 입력되지 않았습니다. "); }
    else if ( checkcontent.value === "") { alert(" 내용이 입력되지 않았습니다. ")}
    else{
        location.href="../comm_more/comm_more.html";
    }
}