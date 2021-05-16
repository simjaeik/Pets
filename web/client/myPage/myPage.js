const URL = "http://ec2-54-180-91-27.ap-northeast-2.compute.amazonaws.com:3000/api";
const jwtToken = sessionStorage.getItem("jwt");
const exist=0;
const updatename = document.getElementById("updatename");
const updateemail = document.getElementById("updateemail");
const updatenickname = document.getElementById("updatenickname");

axios.get(`${URL}/user`,
{ 
    headers : {
        'authorization' : jwtToken
    }
})
.then(response => {
    console.log(response)

    const userNickname = response.data.nickname;
    const username = response.data.name;
    const useremail = response.data.email;

    const nickName = document.getElementById("usernickname");
    nickName.innerText = userNickname;

    const Name = document.getElementById("username");
    Name.innerText = username;

    const Email = document.getElementById("useremail");
    Email.innerText = useremail;
})
.catch(error => {
    console.log(error.response)
}); 

function modal() {

    const zIndex = 9999;   
    const bg = document.createElement('div');
    const modal = document.getElementById('my_modal');
    
    // 모달 div 뒤에 희끄무레한 레이어
    bg.setStyle({
        position: 'fixed',
        zIndex: zIndex,
        left: '0px',
        top: '0px',
        width: '100%',
        height: '100%',
        overflow: 'auto',
        // 레이어 색갈은 여기서 바꾸면 됨
        backgroundColor: 'rgba(0,0,0,0.4)'
    });
    document.body.append(bg);

    // 닫기 버튼 처리, 시꺼먼 레이어와 모달 div 지우기
    modal.querySelector('.modal_btn').addEventListener('click', function() {
        bg.remove();
        modal.style.display = 'none';
    });

    modal.querySelector('#addgroup_btn').addEventListener('click', function() {

        if(updatename.value === "") { alert("이름을 수정해주세요. "); }
        else if(updateemail.value === "") { alert("이메일을 수정해주세요. "); }
        else if(updatenickname.value === "") { alert("닉네임을 수정해주세요. ");}
        else if(exist === 1) { alert("중복된 닉네임입니다. 다시 입력해주세요.");}
        else{
            bg.remove();
            modal.style.display = 'none';

            axios.patch(`${URL}/user`,{ 
                name: updatename.value,
                email: updateemail.value,
                nickName : updatenickname.value
            },{
                headers : {
                    'authorization' : jwtToken
                }
            })
            .then(response => {
                console.log(response)
                alert("성공적으로 수정되었습니다.");
            })
            .catch(error => {
                console.log(error.response)
            }); 
        }
    });

    modal.setStyle({
        position: 'fixed',
        display: 'block',
        boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)',

        // 시꺼먼 레이어 보다 한칸 위에 보이기
        zIndex: zIndex + 1,
        // div center 정렬
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        msTransform: 'translate(-50%, -50%)',
        webkitTransform: 'translate(-50%, -50%)'
    });

}
// Element 에 style 한번에 오브젝트로 설정하는 함수 추가
Element.prototype.setStyle = function(styles) {
    for (var k in styles) this.style[k] = styles[k];
    return this;
};
function initinput(){

    const input = document.getElementsByClassName('input');
    
    for(let i=0;i<input.length;i++){
        input[i].value= "";
    }
}
function isNicknameExist(){

    const checknickname = document.getElementById("updatenickname").value;
    const goodnickname = document.getElementById("goodnickname");

    axios.get(`${URL}/user/nickname/${checknickname}`, {
    })
    .then(response => {
        console.log(response)
        if (response.data.result === true){
            alert("중복된 닉네임입니다.");
            goodnickname.style.display="none";
            exist=1;
        } else{
            goodnickname.style.display="block";
            exist=0;
        }
    })
    .catch(error => {
        console.log(error.response)
    });
};
function addGroup(event){

    const image = document.getElementById("userImage");
    const reader = new FileReader(); 
    reader.onload = function(event){
        image.setAttribute("src",event.target.result);
    }
    reader.readAsDataURL(event.target.files[0]);
}