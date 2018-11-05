var pers;

class Person{
    
    //Constructeur par défaut
    constructor(){
    }   

    // Cette fonction nous est utile pr faire la recherche d'une personne via un certain critère.
    // seul la personne selectionnées sera affichée dans la zone Liste (avec ces détails)
    // Avec les Actions, j'appelle les controleurs (UI)
    getList(){
        console.log('getList');
        console.log('Display value from client after research click button :  ');
        let lastName    = document.getElementById("lastName_search_JS").value;                  //on récupère l'élément de la page HTML sLName. .value veut dire que l'on retourne la valeut au HTML
        let firstName   = document.getElementById("firstName_search_JS").value;
        let params      = "Action=getList&lastName=" + lastName + "&firstName=" + firstName;    // Jenvois les données encodé aux param de la classe Person 
        
        let promise     = doAjaxCall('POST', 'PersonCtrl', params);
        let thisPers    = this;
        
        promise.then(function (data) {                      // Des que les actions ont étés effectuées, afiche moi juste la personne concernée
            console.log('GOT DATA ! Promise fulfilled. ');
            thisPers.displayList(data);
        }, function (error) {
            console.log(error.message);
            document.getElementById("personList_JS").innerHTML = error.message;
        });
    }
    
    
    //Fonction d'affichage pr la ZONE List //On transforme les données recu du serveur en langage HTML !!! 
    displayList(data) {
        console.log("Prepare data to be display");
        
        let persons = new Object();
        
        let htmlTable = "<table>";
            htmlTable += "<td class='colPersonLastNameTitle'>"   + "Noms "       + "</td>";
            htmlTable += "<td class='colPersonFirstNameTitle'>"  + "prénoms "    + "</td>";
            htmlTable += "<td class='colPersonEmailTitle'>"      + "E-mails "    + "</td>";
            htmlTable += "<td class='colPersonIdTitle'>"         + "ID "         + "</td>";
            
        persons = JSON.parse(data);
        for (let i = 0; i < persons.length; i++) {
            htmlTable += "<tr>";
            htmlTable += "<td class='colPersonLastName'>"   + persons[i].lastName  + "</td>";
            htmlTable += "<td class='colPersonFirstName'>"  + persons[i].firstName + "</td>";
            htmlTable += "<td class='colPersonEmail'>"      + persons[i].email     + "</td>";
            htmlTable += '<td class="colPersonId">' + '<a onclick="pers.getOne(' + persons[i].personId + ')" >' + persons[i].personId + '</a></td>';
            htmlTable += "</tr>";
        }
        htmlTable += "</table>";
        let lst = document.getElementById("personList_JS");
        lst.innerHTML = htmlTable;
    }
    
    
    // Par rapport à l'ID de la personne, on va afficher les détails de celui-ci ds la ZONE de détail (pour faire les modif d'une personne...)
    getOne(PersonId){
        console.log('getOne');
        
        let params = "Action=getOne&PersonId=" + PersonId;
        console.log(params);
        console.log(PersonId);
        let promise = doAjaxCall('POST', 'PersonCtrl', params);
        

        promise.then(function (data) {
            let person = JSON.parse(data);
            if (person !== null) {          //On prends les param des Entity
                document.getElementById("personId_detail_JS").value     = person.personId;
                document.getElementById("lastName_detail_JS").value     = person.lastName;
                document.getElementById("firstName_detail_JS").value    = person.firstName;
                document.getElementById("mobile_detail_JS").value       = person.mobile;
                document.getElementById("address_detail_JS").value      = person.address;
                document.getElementById("postalCode_detail_JS").value   = person.postalCode;
                document.getElementById("city_detail_JS").value         = person.city;
                document.getElementById("country_detail_JS").value      = person.country;
                document.getElementById("dateOfBirth_detail_JS").value  = person.dateOfBirth;
               
            }
        }, function (error) {
            console.log('Promise rejected.');
            console.log(error.message);
        });
    }
}

function initPage() {
    pers = new Person();
    pers.getList();
}

function doAjaxCall(method, url, params) {
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