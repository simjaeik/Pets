const URL = "http://ec2-54-180-91-27.ap-northeast-2.compute.amazonaws.com:3000/api";
const jwtToken = sessionStorage.getItem("jwt");
const GID = sessionStorage.getItem("gid");

axios.get(`${URL}/group/${GID}`, {
    headers : {
        'authorization' : jwtToken
    }
    })
    .then(response => {
        console.log(response)
        const gname = document.getElementById("groupname");
        gname.innerText = response.data.name;
        toggleShare();
    })
    .catch(error => {
        console.log(error.response)
});
function toggleShare(){
      
    const share = sessionStorage.getItem("shareoption");
    axios.patch(`${URL}/group/share/${GID}`,{ 
        share : share },{
        headers : {
            'authorization' : jwtToken
        }
    })
    .then(response => {
        console.log(response)
    })
    .catch(error => {
        console.log(error.response)
    }); 
}
// const outputImage =  new Array();
// const outputgInfo = new Array();
// const outputgName = new Array(); 


// axios.get(`${URL}/group`,
// {
//     headers : {
//         'authorization' : jwtToken
//     }
// })
// .then(response => {
//     const output = JSON.parse(response.request.response);
//     console.log(output);

//     gid = output[0]._Group.GID;
//     console.log(gid);
//     for (let i=0;i<output.length;i++){

//         outputImage[i] = output[i]._Group.image;
//         outputgInfo[i] = output[i]._Group.info;
//         outputgName[i] = output[i]._Group.name;

//     }
//    for(let i=0;i<output.length;i++){

//         const g = document.createElement('text');
//         g.classList.add('managegroup');
//         g.innerText = outputgName[i];

//         const ginfo = document.createElement('div');
//         ginfo.classList.add('managegroup');
//         ginfo.innerText = outputgInfo[i];

//         const img = document.createElement('img');
//         const p = document.createElement('p');
//         const addEL = document.createElement('div');

//         addEL.classList.add('item');
 
//         //img.setAttribute("src", event.target.result); 
//         addEL.appendChild(img); 
//         addEL.appendChild(p);
//         addEL.appendChild(ginfo);
//         addEL.appendChild(g);
    
//         document.getElementById('groups').appendChild(addEL);
//     }
// })
// .catch(error => {
//     console.log(error.response)
// }); 
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

        bg.remove();
        modal.style.display = 'none';
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
function updategroup_modal() {

    const zIndex = 9999;   
    const bg = document.createElement('div');
    const modal = document.getElementById('updategroup');
    
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

    modal.querySelector('#updategroup_btn').addEventListener('click', function() {

        const updatename = document.querySelector("#updatename").value;
        const updateinfo = document.querySelector("#updateinfo").value;
        const updatelat = document.querySelector("#updatelat").value;
        const updatelon = document.querySelector("#updatelon").value;        

        if(updatename === "") { alert("바꿀 그룹 이름을 입력해주세요. "); }
        else if(updateinfo === "") { alert("바꿀 그룹 소개를 입력해주세요. "); }
        else if(parseInt(updatelat) === null || parseInt(updatelon) === null) { alert("바꿀 위치를 입력해주세요.") }
        else{
            bg.remove();
            modal.style.display = 'none';

            axios.patch(`${URL}/group/${GID}`,{ 
                name: updatename,
                info: updateinfo,
                image: "aabbccdd",
                latitude: parseInt(updatelat),
                longitude: parseInt(updatelon)
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
// function addGroup(event){

//     const nameinputValue = document.querySelector("#nameinput").value;
//     const infoinputValue = document.querySelector("#infoinput").value;

//     const group = document.createElement('text');
//     group.classList.add('managegroup');
//     group.innerText = nameinputValue;

//     const groupinfo = document.createElement('div');
//     groupinfo.classList.add('managegroup');
//     groupinfo.innerText = infoinputValue;

//     const img = document.createElement('img');
//     const p = document.createElement('p');
//     const addEL = document.createElement('div');
    
//     addEL.classList.add('item');

//     const reader = new FileReader(); 
//     reader.onload = function(event) { 
//         //result : 이미지를 문자열로 
//         //const result = reader.result;
//         img.setAttribute("src", event.target.result); 
//         addEL.appendChild(img); 
//         addEL.appendChild(p);
//         addEL.appendChild(groupinfo);
//         addEL.appendChild(group);
//         setGroup();
//     }; 
//     reader.readAsDataURL(event.target.files[0]);
    
//     document.getElementById('groups').appendChild(addEL);
// }
// function getCategory(){
    
//     const option = [ document.getElementsByName("chk_info")[0],document.getElementsByName("chk_info")[1] ];
    
//     if(option[0].checked == true){
//         return 0;
//     }else if(option[1].checked == true){
//         return 1;
//     }else{
//        return -1;
//     }
// }
// function setGroup(){
    
//     window.navigator.geolocation.getCurrentPosition( function(position) { 
       
//         const gname = document.querySelector("#nameinput").value;
//         const ginfo = document.querySelector("#infoinput").value;
//         const postoption = getCategory();
//         const lat= position.coords.latitude;
//         const lng= position.coords.longitude;
//         const jwtToken = sessionStorage.getItem("jwt");
        
//         axios.post(`${URL}/group`,
//         { 
            
//             name : gname,
//             info : ginfo,
//             image : "aabbccdd",
//             share : 1,
//             latitude : lat,
//             longitude : lng, 
//             },{
//             headers : {
//                 'authorization' : jwtToken
//             }
//         })
//         .then(response => {
//             console.log(response)
//         })
//         .catch(error => {
//             console.log(error.response)
//         }); 
//     });
// }
