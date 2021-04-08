//아이디나 비밀번호가 비어있으면 화면이동이 안되게 한다
function check_blank(){

    let checkid = document.getElementById("ID");
    let checkpw = document.getElementById("PW");
    
    if ( checkid.value === "") { alert(" 아이디가 입력되지 않았습니다. "); }
    else if ( checkpw.value === "") { alert(" 비밀번호가 입력되지 않았습니다. "); }
    else{
        location.href="../community/community.html";
    }
}