const URL = "http://ec2-54-180-91-27.ap-northeast-2.compute.amazonaws.com:3000/api";
const checkid = document.getElementById("ID");
const checkpw = document.getElementById("PW");
function login(){

    axios.post(`${URL}/user/login`, {
        email : checkid.value,
        password : checkpw.value
    })
    .then(response => {
        console.log(response)
        sessionStorage.setItem("jwt",response.data.token);
        location.href="../community/community.html";
    })
    .catch(error => {
        console.log(error.response)
        if (error.response.data.reason === "올바르지 않은 비밀번호 입니다."){
            alert("올바르지 않은 비밀번호 입니다.");
        } else if (error.response.data.reason === "존재하지 않는 사용자 입니다."){
            alert("존재하지 않는 사용자 입니다.");
            initinput();
        }
    });
}
function initinput(){

    const input = document.getElementsByClassName('input');
    
    for(let i=0;i<input.length;i++){
        input[i].value= "";
    }
}
