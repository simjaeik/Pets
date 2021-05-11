const bcrypt = require('bcrypt');

const saltRounds = 10; //이 값을 높일수록 보안이 강화 10정도로 해도 됨.

const encryptPW = async(crypt) =>{
    const cry = await bcrypt.hash(crypt,saltRounds);
    return cry;
} 


module.exports = encryptPW;