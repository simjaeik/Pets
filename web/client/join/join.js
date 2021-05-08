//import axios from 'axios';

function check_join(){
           
    const checkid = document.getElementById("ID");
    const checkpw = document.getElementById("PW");
    const checkpwre = document.getElementById("PWRE");
    const checkemail = document.getElementById("EMAIL");
    
    if ( checkid.value === "") { alert(" 아이디가 입력되지 않았습니다. "); }
    else if ( checkpw.value === "") { alert(" 비밀번호가 입력되지 않았습니다. "); }
    else if ( checkpw.value === "") { alert(" 비밀번호가 입력되지 않았습니다. "); }
    else if ( checkpwre.value === "") { alert(" 비밀번호가 입력되지 않았습니다. "); }
    else if ( checkemail.value === "") { alert(" 이메일이 입력되지 않았습니다. "); }
  
    if ( checkpw.value !== checkpwre.value ) { alert("비밀번호가 일치하지 않습니다."); }
    else{
        axios({
            method : "POST",
            url: `http://host:ec2-54-180-91-27.ap-northeast-2.compute.amazonaws.com:3000/api/user/signUp`,
            data : {
                name : checkid.value,
	            password : checkpw.value,
	            email : checkemail.value,
	            nickName : checkid.value,
            }
        }).then((res)=>{
            console.log(res);
        }).catch(error=>{
            console.log(error);
            throw new Error(error);
        });
    }

}
function goodpw(){

    const pw = document.getElementById('PW');
    const SC = ["!","@","#","$","%","^","&","*","(",")","_","-","+","=","/","~","`"];
    let check_SC=0;

    const badpw = document.getElementById("badpw");
    const badpwoption = document.getElementById("badpwoption");

    const normalpw = document.getElementById("normalpw");
    const normalpwoption = document.getElementById("normalpwoption");

    const goodpw = document.getElementById("goodpw");
    const goodpwoption = document.getElementById("goodpwoption");

    //특수문자 체크
    for(var i=0;i<SC.length;i++){
        if(pw.value.indexOf(SC[i]) !== -1){
            check_SC=1;
        }
    }

    if (check_SC === 0 && pw.value.length < 5){
       badpw.style.display = "block", badpwoption.style.display = "block";
    }
    if(check_SC === 0 && pw.value.length > 5){
        normalpw.style.display = "block"; normalpwoption.style.display = "block";
        badpw.style.display="none"; badpwoption.style.display = "none";
    }
    if (check_SC === 1 && pw.value.length > 8){
        normalpw.style.display = "none"; normalpwoption.style.display = "none";
        badpw.style.display="none"; badpwoption.style.display = "none";
        goodpw.style.display = "block"; goodpwoption.style.display = "block";
    }

}

function eqaul_pw(){
   
    const pw = document.getElementById("PW");
    const pwre = document.getElementById("PWRE");
    const success = document.getElementById("equal");
    const danger = document.getElementById("no_equal");

    if ( pw.value === pwre.value ) { success.style.display = "block"; danger.style.display = "none"; }
    else { danger.style.display = "block"; success.style.display = "none"; }

}