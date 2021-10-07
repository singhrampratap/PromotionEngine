# PromotionEngine

This is a Spring Boot(gradle) based project. I have added code to demonstrate design that is extentible and modular.
There are places where we can refactor and replace the if/else block  using strategy desing pattern.


This has three modules :
1. promoEng-core-module : This has all the businness logic. It uses promoEng-dao-module and promoEng-data-module.
2. promoEng-dao-module  : All interactions with repository. It supports swapping the storage (i.e. DB).
3. promoEng-data-module : It has all the DTO's.

