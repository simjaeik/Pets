const URL = "http://ec2-54-180-91-27.ap-northeast-2.compute.amazonaws.com:3000/api";
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

        const name = document.querySelector("#nameinput").value;
        const info = document.querySelector("#infoinput").value;
        const postoption = getCategory();

        if(name === "") { alert("그룹 이름을 입력해주세요. "); }
        else if(info === "") { alert("그룹 소개를 입력해주세요. "); }
        else if(postoption === -1) { alert("게시글 종류를 선택해주세요. ");}
        else{
            bg.remove();
            console.log(postoption);
            modal.style.display = 'none';
        }// setGroup();
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
function addGroup(event){

    const nameinputValue = document.querySelector("#nameinput").value;
    const infoinputValue = document.querySelector("#infoinput").value;

    const group = document.createElement('text');
    group.classList.add('managegroup');
    group.innerText = nameinputValue;

    const groupinfo = document.createElement('div');
    groupinfo.classList.add('managegroup');
    groupinfo.innerText = infoinputValue;

    const img = document.createElement('img');
    //이미지 url 
    const p = document.createElement('p');
    
    const addEL = document.createElement('div');
    
    addEL.classList.add('item');
    
    const reader = new FileReader(); 
    reader.onload = function(event) { 
        img.setAttribute("src", event.target.result); 
        addEL.appendChild(img); 
        addEL.appendChild(p);
        addEL.appendChild(groupinfo);
        addEL.appendChild(group);
    }; 
    reader.readAsDataURL(event.target.files[0]);
    document.getElementById('groups').appendChild(addEL);
}

function getCategory(){
    
    const option = [ document.getElementsByName("chk_info")[0],document.getElementsByName("chk_info")[1] ];
    
    if(option[0].checked == true){
        return option[0].value;
    }else if(option[1].checked == true){
        return option[1].value;
    }else{
       return -1;
    }
}
function setGroup(){

    const categoryvalue = getCategory();
    
    window.navigator.geolocation.getCurrentPosition( function(position) { 
       
        const lat= position.coords.latitude;
        const lng= position.coords.longitude;
        console.log(lat);
    //     axios.post(`${URL}/group`, {
    //         name : group.value,
    //         info : groupinfo.value,
    //         image : 이미지,
    //         share : categoryvalue,
    //         latitude : lat,
    //         longitude : lng,
    //     })
    //     .then(response => {
    //         console.log(response)
    //     })
    //     .catch(error => {
    //         console.log(error.response)
    //     }); 
    });
}