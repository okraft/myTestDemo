	function setCookie(name, value, expires, path, domain, secure) { 
        if(!expires){
            expires = 31;
        }

        var expireDate;
        if(!isNaN(expires)){
            expireDate = new Date();
            expireDate.setDate(expireDate.getDate() + expires);
        }else if(expires.toGMTString){
            expireDate = expires;
        }

        document.cookie= name + "=" + escape(value) +
        ((expireDate) ? "; expires=" + expireDate.toGMTString() : "") +
	    ((path) ? "; path=" + path : "") + 
	    ((domain) ? "; domain=" + domain : "") + 
	    ((secure) ? "; secure" : ""); 
	} 
	
	function getCookie(name)
	{ 
	    var dc = document.cookie; 
	    var prefix = name + "="; 
	    var begin = dc.indexOf("; " + prefix);  
	 
	
	
	    if (begin == -1)
	    { 
	        begin = dc.indexOf(prefix); 
	        if (begin != 0) return null;  
	    }
	    else
	    { 
	        begin += 2; 
	    } 
	    var end = document.cookie.indexOf(";", begin); 
	    if (end == -1) 
	    { 
	        end = dc.length; 
	    } 
	    
		var value = unescape(dc.substring(begin + prefix.length, end));
		return value;
	}