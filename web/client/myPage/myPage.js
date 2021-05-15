const URL = "http://ec2-54-180-91-27.ap-northeast-2.compute.amazonaws.com:3000/api";
const jwtToken = sessionStorage.getItem("jwt");

// axios.get(`${URL}/user`,
// { 
//     headers : {
//         'authorization' : jwtToken
//     }
// })
// .then(response => {
//     console.log(response)
 
//     // {
//     //     name : 이름,
//     //     email : 이메일,
//     //     nickName : 유저 닉네임
//     // }
// })
// .catch(error => {
//     console.log(error.response)
// }); 

const username = "micky";
const Name = document.getElementById("userName");
Name.innerText = username;
