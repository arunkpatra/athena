-- Historical, yearly breakage rate calculation
-- Description: Given an as-of-year, calculates breakage rates by card type, e.g. produce the result below:

-- | card_type_code | total_purchase_value_of_card_type | total_breakage_for_card_type 	| breakage_rate | as_of_year |
-- | :--- 			| :--- 							 	| :--- 							| :--- 			| :--- 		 |
-- | G-0001         | 395.00                            | 98.00 						| 24.8100 		| 2019 		 |
-- | G-0002         | 150.00                            | 7.50 							| 5.0000 		| 2019 		 |
-- | G-0004         | 25.00 							| 3.00 							| 12.0000 		| 2019 		 |
-- | G-0005         | 50.00 							| 18.00 						| 36.0000 		| 2019 		 |
-- | G-0006         | 75.00 							| 3.00 							| 4.0000 		| 2019 		 |
-- | G-0007         | 50.00                             | 2.00 							| 4.0000 		| 2019 		 |
-- | G-0034         | 25.00 							| 5.00 							| 20.0000 		| 2019 		 |
-- | G-0035         | 75.00 							| 19.00 						| 25.3300 		| 2019 		 |

-- Note: Assume G-0001 is equivalent to "Happy Student eCard"

select transaction.gc_type_code                                                 as card_type_code,
       sum(card.gc_original_value)                                              as total_purchase_value_of_card_type,
       (total_purchase_value_of_card_type - sum(tx_value))                      as total_breakage_for_card_type,
       (total_breakage_for_card_type / total_purchase_value_of_card_type) * 100 as breakage_rate,
       2019                                                                     as as_of_year
from transaction,
     card
where transaction.tx_type = 'REDEMPTION'
  and date_part('year', card.gc_expiry_date) = 2019
  and transaction.gc_uuid = card.gc_uuid
group by transaction.gc_type_code
order by transaction.gc_type_code
