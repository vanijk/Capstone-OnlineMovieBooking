function getQ(){
  alert("test");
  const currentURL = new URLSearchParams(window.location.search);
  alert(currentURL);
  const id = currentURL.get('si-email');
  alert(id);

}
