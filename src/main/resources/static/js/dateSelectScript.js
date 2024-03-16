function dateButtonOnClick(event){
    const elem = event.id;
    const elemValue = event.value;

    const formElement = document.createElement("form");
    formElement.method = "GET";
    formElement.action = "/getMoviesByDate"

    const inputElement = document.createElement("input");
    inputElement.name = "date";
    inputElement.value = elemValue;
    formElement.appendChild(inputElement);
    document.body.appendChild(formElement);
    formElement.submit();

    }