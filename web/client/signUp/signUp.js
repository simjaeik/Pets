const checkname = document.getElementById("NAME");
const checkpw = document.getElementById("PW");
const checkpwre = document.getElementById("PWRE");
const checkemail = document.getElementById("EMAIL");
const checknickname = document.getElementById("NICKNAME");
const checkbtn = document.getElementById("checkbtn");
const URL = "http://ec2-54-180-91-27.ap-northeast-2.compute.amazonaws.com:3000/api";

function signUp(){
    axios.post(`${URL}/user/signUp`, {
        name : checkname.value,
        password : checkpw.value,
        email : checkemail.value,
        nickName : checknickname.value
    })
    .then(response => {
        console.log(response)
    })
    .catch(error => {
        console.log(error.response)
        if(error.response.data === "중복된 닉네임입니다.")
            alert(error.response.data);
    });

}
function isNicknameExist(){

    const nickName = checknickname.value;
    axios.get(`${URL}/user/nickname/:${nickName}`, {
    })
    .then(response => {
        console.log(response)
    })
    .catch(error => {
        console.log(error.response)
    });
}
function isEmailExist(){

    const email = checkemail.value;
    axios.get(`${URL}/user/email/:${email}`, {
    })
    .then(response => {
        console.log(response)
    })
    .catch(error => {
        console.log(error.response)
    });
}
function check_join(){
   
    if ( checkname.value === "") { alert(" 이름이 입력되지 않았습니다. "); }
    else if ( checkpw.value=== "") { alert(" 비밀번호가 입력되지 않았습니다. "); }
    else if ( checkpw.value === "") { alert(" 비밀번호가 입력되지 않았습니다. "); }
    else if ( checkpwre.value === "") { alert(" 비밀번호가 입력되지 않았습니다. "); }
    else if ( checkemail.value === "") { alert(" 이메일이 입력되지 않았습니다. "); }
    else if ( checknickname.value === "") { alert("닉네임이 입력되지 않았습니다."); }
    else if ( checkpwre.value!== checkpw.value) { alert("비밀번호가 일치하지 않습니다."); }

}
function goodpw() {

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
   
    const success = document.getElementById("equal");
    const danger = document.getElementById("no_equal");

    if ( checkpw.value === checkpwre.value ) { success.style.display = "block"; danger.style.display = "none"; }
    else { danger.style.display = "block"; success.style.display = "none"; }

}