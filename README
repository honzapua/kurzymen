# Kurzy men

Projekt uklada data menovych kurzu vzhledem k ceske korune z CSAS a fixer.io.
V pripade, ze se lisi kurzy z obou zdroju o vice nez 0.01 odesle email s dotycnymi kurzy.
Odesilani probiha kazdou hodinu.

## Instalace

1. instalace databazoveho schematu a uzivatele:
    `kurzymen\src\db\create-schema.sql`
    (nutno zmenit nazev schematu a uzivatele podle potreby)
    spoutstet pomoci `mysql -u root --password=password < create-schema.sql`
2. instalace databazovych objektu:
    `kurzymen\src\main\resources\schema-disabled.sql`
    spoustet pomoci `mysql -u uzivatel --password=heslo schema < schema-disabled.sql`
    (schema, uzivatel a heslo jsou vytvoreni krokem 1)
3. konfigurace aplikace:
    1. V adresari `kurzymen\src\main\resources\` vytvorit soubor `application.properties`
        ze souboru `application.properties.template`. (Schema MySQL databaze, uzivatel, heslo,
        jmeno, heslo Gmail uctu pro odesilani.
    2. V adresari `kurzymen\src\main\resources\kurzy\men\client\csas\impl\` vytvorit soubor
        `webapikey.properties` ze souboru `webapikey.properties.template`. (Vyplnit web API-Key CSAS prostredi.)
    3. V adresari `kurzymen\src\main\resources\kurzy\men\services\impl\currencycomparator\`
        zalozit soubor `recipients.txt` ze souboru `recipients.txt.template`. (vyplnit
        emailove adresy prijemcu)
4.  `mvn clean install`
5. Nadeployovat `kurzymen.war` z adresare `kurzymen\target`


