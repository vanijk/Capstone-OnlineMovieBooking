function seatclick(event){

    var elementSelect = event.id;
    var classname1 = document.getElementById(elementSelect).className;

    if(classname1 != "path" && classname1 != "booked"  ){
        if(classname1 == "extracare fa fa-wheelchair"){

            document.getElementById(elementSelect).className = "bookedbyscript fa fa-wheelchair ";
            if(classname1 != "bookedbyscript fa fa-wheelchair"){
                increament(elementSelect);
            }
        }else {

            document.getElementById(elementSelect).className = "bookedbyscript";
            if(classname1 != "bookedbyscript"){
                increament(elementSelect);
            }
        }

        //updateRight(elementSelect);
    }
    if(classname1 == "bookedbyscript"){
        document.getElementById(elementSelect).className = "seat";
        decreament(elementSelect);
    }
    if(classname1 == "bookedbyscript fa fa-wheelchair "){
        document.getElementById(elementSelect).className = "seat fa fa-wheelchair";
        decreament(elementSelect);
    }



}
function increament(elementSelect){

    let c ;
    const ticketCount = document.getElementById("ticket-count");
    const spticketcount = document.getElementById("ticketcont");
  //  alert(ticketCount.value);
    let  ticketValue = document.getElementById("tickets1").value;
    document.getElementById("tickets1").value = ticketValue + elementSelect+",";



    if(ticketCount.value == ""){
        c = 0
    }else{
        let n = ticketCount.value//ticketCount.innerText;
        c = parseInt(n,10);

    }
    ticketCount.value =c+1;
    spticketcount.innerText = c+1;
   // alert(ticketCount.value);
   // let tickets =document.getElementsByName("tickets");
   // document.getElementsByName("tickets1") = "bjbj";


}
function decreament(elementSelect){

    let  ticketValue = document.getElementById("tickets1").value;

    let val = elementSelect+","
    let newValue = ticketValue.replace(val,"");

    document.getElementById("tickets1").value = newValue;



    let c ;
    const ticketCount = document.getElementById("ticket-count");
    const spticketcount = document.getElementById("ticketcont");
    if(ticketCount.value != ""){
        let n = ticketCount.value;
        c = parseInt(n,10);
        ticketCount.value =c-1;
        spticketcount.innerText = c-1;
    }
}
function updateRight(elementSelect){
    let c ;
    const ticketCount = document.getElementById("ticket-count");
    if(ticketCount.innerText == ""){
        c = 0
      //  alert(c);
    }else{
        let n = ticketCount.innerText;
        c = parseInt(n,10);

    }
    ticketCount.innerText =c+1;
}