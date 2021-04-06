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

function eqaul_pw(){
   
    let pw = document.getElementById("PW");
    let pwre = document.getElementById("PWRE");
    let success = document.getElementById("equal");
    let danger = document.getElementById("no_equal");

    if ( pw.value === pwre.value ) { success.style.display = "block"; danger.style.display = "none"; }
    else { danger.style.display = "block"; success.style.display = "none"; }

}