# PromotionEngine

This is a Spring Boot(gradle) based project. I have added code to demonstrate design that is extentible and modular.
There are places where we can refactor and replace the if/else block  using strategy desing pattern.

Also core-module can be sub-divided in promoRules(metadata service/or DB scheam owernership to this module) and Cart(including SKU) in other module.


This has three modules :
1. promoEng-core-module : This has all the businness logic. It uses promoEng-dao-module and promoEng-data-module.
2. promoEng-dao-module  : All interactions with repository. It supports swapping the storage (i.e. DB).
3. promoEng-data-module : It has all the DTO's.

