Client per Flows
===

Legenda
---
{urlapp} = url root dell'applicazione

es. https://helpdesk.amministrazione.cnr.it/hd

{instanza} = schema database

es. HDSiper, HDISSN, HDBice, HDPeople, HDConcorsi, HDRSI


Gestione Categoria
---

{URL} = {urlapp}/rest/catg

GET (queries) [ALL]:
{URL}/{istanza} restituisce la lista delle categorie
{URL}/{istanza}/{id} restituisce la categoria id + la lista delle sue sottocategorie

PUT (inserimento) [ADMIN]:
{URL}/{istanza} + json

es.: (l'id viene assegnato automaticamente) 
{
"idPadre":1,
"nome":"il nome",
"descrizione":"la descrizione"
}

POST (modifica) [ADMIN]:
{URL}/{istanza} + json

es.: 
{
"id":2,
"idPadre":1,
"nome":"il nome",
"descrizione":"la descrizione"
}

DELETE (disabilitazione) [ADMIN]:
{URL}/{istanza}/{id} disabilita la categoria id


Gestione Utente
---

{URL} = {urlapp}/rest/user

GET (queries) [ADMIN]:
{URL}/{istanza} restituisce la lista degli utenti esperti
{URL}/{istanza}/{uid} restituisce il dettaglio dell'utente uid

PUT/POST (inserimento/modifica) [ADMIN]:
{URL}/{istanza} + json

es.: 
{
"firstName":"nome",
"familyName":"cognome",
"profile":2,
"login":"nome.cognome",
"email":"nome.cognome@cnr.it",
"struttura":"1"
}

DELETE (disabilitazione) [ADMIN]:
{URL}/{istanza}/{uid} disabilita l'utente uid


Gestione Esperto
---

{URL} {urlapp}/rest/ucat

GET (queries) [ADMIN,EXPERT,VALIDATOR]:
{URL}/{istanza}/{id}  se id numerico restituisce la lista degli esperti assegnati alla categoria id

{URL}/{istanza}/{uid} altrimenti restituisce la lista delle categorie assegnate all'esperto uid

PUT/POST (assegnazione) [ADMIN]:
{URL}/{istanza}/{id}/{uid} assegna la categoria id all'esperto uid

DELETE (deassegnazione) [ADMIN]:
{URL}/{istanza}/{id}/{uid} rimuove l'assegnazione della categoria id all'esperto uid


Gestione Problema Utente Esterno
---

{URL} = {urlapp}/rest/pest

PUT (inserimento)
{URL}/{istanza} + json

es.: 
{
"firstName":"nome",
"familyName":"cognome",
"email":"nome.cognome@cnr.it",
"titolo":"Oggetto della segnalazione",
"descrizione":"Testo della segnalazione",
"categoria":1,
"categoriaDescrizione:"Problema"
"confirmRequested":"y"
}

POST (aggiunta nota)
{URL}/{istanza} + json

es.: 
{
"idSegnalazione":2,
"nota":"testo della nota",
"stato":0
}

POST (aggiunta allegato)
{URL}/{istanza}/{id} + multipart/form-data ["allegato", binary data]

