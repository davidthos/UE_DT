var pers;

class Person{
    
    //Constructeur par défaut
    constructor(){
    }   
    
   
    
    
    //Va nous permettre d'afficher la liste des personne dans la zone liste
    // Avec les Actions, j'appelle les controleurs
    getList(){
         
        let lastName    = document.getElementById("lastName_search_JS").value; //on récupère l'élément de la page HTML sLName. .value veut dire que l'on retourne la valeut au HTML
        let firstName   = document.getElementById("firstName_search_JS").value;
        let params      = "Action=getList&lastName=" + lastName + "&firstName=" + firstName; 
        
        let promise     = doAjaxCall('POST', 'PersonCtrl', params);
        let thisPers    = this;
        
        promise.then(function (data) {
            thisPers.displayList(data);
        }, function (error) {
            console.log(error.message);
            document.getElementById("personList_JS").innerHTML = error.message;
        });
    }
    
    // Par rapport à l'ID de la personne, on va afficher les détails de celui-ci ds la zone de détail (pour faire les modif d'une personne...)
    getOne(PersonId){
        let params = "Action=getOne&PersonId=" + PersonId;
        let promise = doAjaxCall('POST', 'PersonCtrl', params);
        

        promise.then(function (data) {
            let person = JSON.parse(data);
            if (person !== null) {
                document.getElementById("personId_detail_JS").value = person.personId_detail_JS;
                document.getElementById("lastName_detail_JS").value = person.lastName_detail_JS;
                document.getElementById("firstName_detail_JS").value = person.firstName_detail_JS;
               
            }
        }, function (error) {
            console.log('Promise rejected.');
            console.log(error.message);
        });
    }
    
    // On transforme les données recu en langage HTML !!! VOIR ma boucle !!!
    displayList(data) {
        console.log("Prepare data to be display");
        let person = new Object();
        let htmlTable = "<table>";

        articles = JSON.parse(data);
        for (let i = 0; i < person.length; i++) {
            htmlTable += "<tr>";
            htmlTable += "<td class='colPersonName'>" + person[i].lastName_detail_JS + "</td>";
            htmlTable += "<td class='colPersonDesc'>" + person[i].person.firstName_detail_JS + "</td>";
            htmlTable += '<td class="colPersonId">' + '<a onclick="pers.getOne(' + person[i].personId_detail_JS + ')" >' + person[i].personId_detail_JS + '</a></td>';
            htmlTable += "</tr>";
        }
        htmlTable += "</table>";
        let lst = document.getElementById("personList_JS");
        lst.innerHTML = htmlTable;
    }
    
    
}

function initPage() {
    pers = new Person();
    pers.getList();
}

function doAjaxCall(method, url,params) {
    var promise = new Promise(function (resolve, reject) {
        var request = new XMLHttpRequest();

        request.open(method, url, true);
        request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        request.send(params);
        request.onreadystatechange = function () {
            if (request.readyState === 4) {
                if (request.status === 200) {
                    resolve(request.response); // we got data here, so resolve the Promise
                } else {
                    reject(Error(request.statusText)); // status is not 200 OK, so reject
                }
            } else {
                console.log("request processing going on");
            }
        };

        request.onerror = function () {
            reject(Error('Error fetching data.')); // error occurred, reject the Promise
        };
    });
    return promise;
}