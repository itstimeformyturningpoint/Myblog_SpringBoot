let index = {
    init: function(){//제이쿼리
        $("#btn-save").on("click", ()=>{ // function(){} , ()=>{} this를 바인딩하기 위해서!! 
            this.save();
        });
        
        
    },

    save: function(){
        
        let data ={
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        
       //console.log(data);
       $.ajax({
           type: "POST",
           url: "/auth/joinProc",
           data: JSON.stringify(data), //httpBody data
           contentType : "application/json; charset=utf-8",
           //dataType:"json"
           

       }).done(function(resp){
           alert("회원가입이 완료 되었습니다.");
           location.href="/";

       }).fail(function(error){
           alert(JSON.stringify(error));

       });//3개의 데이터를 제이슨으로 변경 하여 insert 요청 
    }
    
    }
    


index.init();