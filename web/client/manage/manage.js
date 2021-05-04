function modal() {

    let zIndex = 9999;
    let modal = document.getElementById('my_modal');

    // 모달 div 뒤에 희끄무레한 레이어
    var bg = document.createElement('div');
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
    modal.querySelector('.modal_close_btn').addEventListener('click', function() {
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

function addGroup(){
    
    const group = document.getElementsByID("addgroup");

    const newgroup = document.createElement('div');
    newgroup.classList.add("item");
}
// const newcommentEL = document.getElementById("new-comment");
//     const newcomment = newcommentEL.value.trim(); //trim() : 공백제거

//     if(newcomment){

//         const dateEL = document.createElement('div');
//         dateEL.classList.add("comment-date");
//         const dateString = dateToString(new Date());
//         dateEL.innerText = dateString;

//         const contentEL = document.createElement('div');
//         contentEL.classList.add('coment-content');
//         contentEL.innerText = newcomment;
  
//         const commentEL = document.createElement('div');
//         commentEL.classList.add('comment-content');
//         commentEL.appendChild(contentEL);
//         commentEL.appendChild(dateEL);
       

//         document.getElementById('comments').appendChild(commentEL);
//         newcommentEL.value="";     

//         const countEL = document.getElementById('comments-count');
//         const count = countEL.innerText;
//         countEL.innerText = parseInt(count) + 1;
