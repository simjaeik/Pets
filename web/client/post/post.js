const radiobtn = document.getElementsByName("chk_info");
const checktitle = document.getElementById("posttitle");
const checkname = document.getElementById("writername");
const checkcontent = document.getElementById("content");
const URL = "http://ec2-54-180-91-27.ap-northeast-2.compute.amazonaws.com:3000/api";

function initpost(){

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
    modal.querySelector('#no').addEventListener('click', function() {
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
// Element 에 style 한번에 오브젝트로 설정하는 함수 추가
Element.prototype.setStyle = function(styles) {
    for (var k in styles) this.style[k] = styles[k];
    return this;
};
function check_blank(){


    let cnt = 0, check = false;
    for (let i=0;i<radiobtn.length;i++){
        
        if(radiobtn[i].checked === false) { cnt++; }
        
        if ( cnt === radiobtn.length) { check = false; }
        else { check = true; } 
    }

    if ( check === false) { alert("게시물을 선택해주세요. "); }
    else if ( checktitle.value === "") { alert(" 제목이 입력되지 않았습니다. "); }
    else if ( checkname.value === "") { alert(" 닉네임이 입력되지 않았습니다. "); }
    else if ( checkcontent.value === "") { alert(" 내용이 입력되지 않았습니다. ")}

}
function getTime() {

   const d = new Date();
   const s =
    leadingZeros(d.getFullYear(), 4) + '-' +
    leadingZeros(d.getMonth() + 1, 2) + '-' +
    leadingZeros(d.getDate(), 2) + ' ' +

    leadingZeros(d.getHours(), 2) + ':' +
    leadingZeros(d.getMinutes(), 2) + ':' +
    leadingZeros(d.getSeconds(), 2);

    return s;
}
function leadingZeros(n, digits) {
    var zero = '';
    n = n.toString();
  
    if (n.length < digits) {
      for (i = 0; i < digits - n.length; i++)
        zero += '0';
    }
    return zero + n;
}
  
function setPost(){

    const datevalue = getTime();
    alert(datevalue);
    // axios.post(`${URL}/post`, {
    //     GID : 11,
    //     title : checktitle.value,
    //     content : checkcontent.value,
    //     date : `${getTime()}`,
    //     category : 
    // })
    // .then(response => {
    //     console.log(response)
    //     location.href="../community/community.html";
    // })
    // .catch(error => {
    //     console.log(error.response)
    // });
}