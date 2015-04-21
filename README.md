To run: java -jar directory-to-jar_file option
example: java -jar out/artifacts/VendingMachineKata_jar/VendingMachineKata.jar DOLLAR QUARTER COIN_RETURN



Options:
    "NICKEL" => acceptMoney() returns ".05"
    "DIME" => acceptMoney() returns ".10"
    "QUARTER" => acceptMoney() returns ".25"
    "DOLLAR" => acceptMoney() returns "1.00"

    "COIN_RETURN" => returnMoney() displays the money inserted
    "GET_A" => selectItem()
    "GET_B" => selectItem()
    "GET_C" => selectItem()

    adding items:  you can add a collection of items as follows

    selector;cost;count,selector;cost;count example "F;2.50;6,D;1.50;3"

    if the item already exists, the item is updated
