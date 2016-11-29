# Sem3_Lab6
Lab6_7( | )

**Пакет by.sem3.util** содержит классы для работы с форматами данных(xml, json), чтения файлов csv, записи файлов xml и json, а также логгера для ведения статистики работы приложения. Данный пакет не зависит от проекта и может быть использован в других проектах.
*******
**Пакет by.sem3.lab6_7** содержит классы для выполнения сугубо текущего задания:
**Company** - класс-сущность определяющий компанию.
**CompanyUtil** - класс для работы с классом Company.
**Companies** - класс содержащий список всех компаний и выполняющий различные действия с данным списком.
**Functional** - класс определяющий логику работы приложения.
**Requests** - класс осуществляющий работу с запросами.

Метод **Functional.isSQLRequestSelect(String request)** осуществляет проверку SQL-запроса SELECT на корректность, а также на то, поддерживается ли данный запрос приложением.

**Поддерживаемые SQL-запросы:**

SELECT /*названия полей через запятую или одно из ключевых слов (ALL или *)*/ FROM DATABASE;

SELECT /*названия полей через запятую или одно из ключевых слов (ALL или *)*/ FROM DATABASE WHERE /*название поля*/ BETWEEN /*значение*/ AND /*значение*/;

SELECT /*названия полей через запятую или одно из ключевых слов (ALL или *)*/ FROM DATABASE WHERE /*название поля*/ /*оператор сравнения*/ /*значение*/;

SELECT /*названия полей через запятую или одно из ключевых слов (ALL или *)*/ FROM DATABASE WHERE /*название поля*/ /*оператор сравнения*/ /*значение*/ /*оператор AND или OR*/ /*название поля*/ /*оператор сравнения*/ /*значение*/;

Если значение строковое, то его необходимо брать в одинарные кавычки, то есть 'строковое значение'.
Также, при перечислении полей, их должно быть как минимум 2.
Регистр вводимых SQL-запросов не имеет значения, за исключением значений полей.
*********
Метод **Requests.requestSQL(Companies companies, String request)** производит работу с SQL-запросом. Поле patternColumns из данного метода определяет список возможных названий полей, а также ключевое слово ALL и с помощью группировочных скобок разбивает их на группы, которые будут использоваться для дальнейшего разбиения SQL-запроса.

Метод **Requests.parseSQLRequestOnColumns(Pattern patternColumns, Scanner scanner)** производит разбиение SQL-запроса с целью получения названий полей(то есть пока не встретит слово FROM) и возвращает список найденых полей.

Метод **Requests.parseSQLRequestOnLogics(Scanner scanner)** производит дальнейшее разбиение SQL-запроса, то есть разбиение на составляющие предикатов.

Методы **Requests.getLogicOperator(int length, Matcher matcher) и Requests.logicOperators(int number)** с помощью группы с совпадением из matcher получают оператор сравнения и возвращают его.

Методы **Requests.numbersOfGroups(List\<String> columns, Pattern patternColumns) и Requests.numberOfColumn(Matcher matcher)** создают и заполняют массив, с помощью групп из patternColumns и групп с совпадением из matcher, элементами которого будут являтся номера групп из patternColumns, а они эквивалентны номерам полей. То есть получается, что возвращаемый массив будет содержать номера полей в том порядке, в котором их ввели.

Методы **Requests.formatOfColumns(int[] groups) и Requests.formatsOfColumns(int numberOfColumn)** создают формат для вывода названий и значений полей на консоль с помощью массива groups содержащего номера полей.

Методы **Requests.fillNamesOfColumns(int[] groups, int numberOfColumns) и Requests.namesOfColumns(int numberOfColumn)** создают массив названий полей.

Метод **Requests.selectionSQL(Companies companies, List\<String> logics, String format, String[] columnsNames, int[] groups, Pattern patternColumns, int numberOfColumns)** производит выборку элементов из companies по введённому SQL-запросу.

Метод **Requests.getLogicsResult(List\<String> logics, Company company, Pattern patternColumns)** производит вычисление предиката идущего в SQL-запросе после WHERE и возвращает результат.

Метод **Requests.getResultOfBetweenPredicate(Iterator\<String> iterator, String field)** проиводит вычисление предиката с ключевым словом BETWEEN.

Метод **Requests.getResultOfPredicateWithComparisonOperator(String field, String rightElement, String comparisonOperator)** производит вычисление предиката с оператором сравнения.

Метод **Requests.getResultOfPredicateWithLogicOperator(boolean left, boolean right, String logicOperator)** производит вычисление предиката с логическими операторами AND и OR.

Метод **Requests.getColumn(int numberOfColumn, Company company)** возвращает значение поля company по его номеру.

Метод **Requests.fillColumns(int[] groups, Company company, int numberOfColumns)** возвращает массив значений введённых полей элемента company.

Метод **Requests.printNamesOfColumns(String format, String[] namesOfColumns)** выводит на консоль названия полей.

Метод **Requests.printElement(String format, String[] columns)** выводит значения полей элемента.

Методы **Requests.getElementAsXML(int[] groups, Company company), Requests.getElementAsJSON(int[] groups, Company company), Requests.getArrayAsXML(int[] groups, List\<Company> selection, int numberOfColumns) и Requests.getArrayAsJSON(int[] groups, List\<Company> selection, int numberOfColumns)** производят составление файлов формата XML и JSON соответственно, на основании выборки.

Подобная структура разбиения SQL-заброса позволяет достаточно легко её модифицировать.
*******
**Известные на данный момент баги:**

-Некорректное сравнение дат в SQL-запросах.
 *Пояснение: из-за представления даты в виде DD.MM.YYYY вначале сравнивается день, потом месяц и только потом год, а как следствие если у одной даты день больше дня второй даты, то первая дата будет считаться большей, хотя это может быть и не так.
 *Пример: при сравнении 13.11.2016 и 14.12.2015, первая дата будет меньше второй, хотя это не так.
 *Возможное решение: преобразование даты в формат YYYYMMDD при сравнении.
