function check_blank(){
           
    let checkid = document.getElementById("ID");
    let checkpw = document.getElementById("PW");
    let checkpwre = document.getElementById("PWRE");
    let checkemail = document.getElementById("EMAIL");

    if ( checkid.value === "") { alert(" 아이디가 입력되지 않았습니다. "); }
    else if ( checkpw.value === "") { alert(" 비밀번호가 입력되지 않았습니다. "); }
    else if ( checkpw.value === "") { alert(" 비밀번호가 입력되지 않았습니다. "); }
    else if ( checkpwre.value === "") { alert(" 비밀번호가 입력되지 않았습니다. "); }
    else if ( checkemail.value === "") { alert(" 이메일이 입력되지 않았습니다. "); }
    else{
        location.href="community.html";
    }
}
/* 
 let check = [ $('#ID'), $('#PW'), $('#PWRE'), $('#EMAIL') ];
    for (let i=0; i<4; i++){
        if (check[i].value === "") { alert("모든 문항을 다 채워주세요. "); }
    }
*/