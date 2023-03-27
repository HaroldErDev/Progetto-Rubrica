# Progetto Rubrica

Progetto che si basa sul realizzare una rubrica telefonica con l'utilizzo di SWING in Java.

Per far funzionare il progetto è necessario che sia presente dentro la stessa directory in cui si trova il .jar eseguibile un file di nome "credenziali_database.properties", che è possibile trovare nella cartella resources presente nel progetto, e modificare le proprietà a seconda delle proprie credenziali di MySQL. Inoltre è necessario eseguire lo script sql (sempre presente nella cartella resources del progetto) in MySQL per poter creare lo schema con le relative tabelle e colonne.

# Considerazioni di utilizzo

Il seguente progetto non è del tutto completo e sicuro.

- Mancanza della "registrazione" di un nuovo utente. Per poter effettuare il LOGIN è necessario inserire un nuovo utente da MySQL, inserendo username e password.
- La password é del tutto visibile nel data base (non è criptata).
