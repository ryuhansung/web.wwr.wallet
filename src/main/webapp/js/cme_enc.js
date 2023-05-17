loadScript('/js/dmenc/tea-block.js', 0,null);
loadScript('/js/dmenc/base64.js', 0,null);
loadScript('/js/dmenc/utf8.js', 0,null);
loadScript('/js/dmenc/jsbn.js', 0,null);
loadScript('/js/dmenc/rsa.js', 0,null);
loadScript('/js/dmenc/enc_aes.js', 0,null);

function GenerateKey(){
	time = new Date().getTime();
	random = Math.floor(65536*Math.random());
	return (time*random).toString();	
}

function EncryptTEA(k, text){	
	return Tea.encrypt(text, k);	
}

function DecryptTEA(k, text){
	return Tea.decrypt(text,k);
}

function EncryptRSA(m, e, text){
	var rsa = new RSAKey();
	rsa.setPublic(m, e);
	return rsa.encrypt(text);
}

function EncryptAES(plain_text, k)
{
	GibberishAES.size(256);	
	
	var enc =  GibberishAES.aesEncrypt(plain_text, k);
	enc = enc.replace(/&/g,"^26"); 
	enc = enc.replace(/\+/g,"^2B"); 
	enc = enc.replace(/\//g,"^47"); 
	return enc;
}

function DecryptAES(base64_text, k)
{
	
	base64_text = base64_text.replace(/\^2B/gi, "+");
	base64_text = base64_text.replace(/\^26/gi, "&");
	base64_text = base64_text.replace(/\^47/gi, "/");
	GibberishAES.size(256);
	return GibberishAES.aesDecrypt(base64_text, k);
}