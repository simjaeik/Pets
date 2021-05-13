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
        localStorage.setItem(`${checkid.value}-token`,response.data.token);
        location.href="../community/community.html";
    })
    .catch(error => {
        console.log(error.response)
        if (error.response.data.message === "Missing credentials"){
            alert("아이디 혹은 비밀번호가 틀렸습니다.");
        }
    });
}
