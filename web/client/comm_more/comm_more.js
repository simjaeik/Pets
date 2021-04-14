function changeSort(){

    let t1 = document.getElementById('changesort1');
    let t2 = document.getElementById('changesort2');

    if (t1.innerText === "시간순")   {   t1.innerText = "추천순"; t2.innerText = "시간순"; }
    else {  t1.innerText="시간순";  t2.innerText="추천순"; } 
}
function clonenode(){

    let post = document.getElementById("post2");
    let cln = post.cloneNode(true);
    document.getElementById("post3").appendChild(cln);

}