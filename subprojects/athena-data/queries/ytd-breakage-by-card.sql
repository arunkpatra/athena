-- YTD Breakage on cards as of today
-- Description: Find out the total breakage for a Give card type

-- Verified: OK

-- Approach
-- 1. Which cards have expired as of today in the last one year?
-- 2. What was the breakage on each such card?
-- 3. Group by card type and sum ALL breakages. That gives the total breakage for a card type

select CC.gc_name, CC.gc_type_code card_type_code, BB.total_breakage_value as ytd_breakage, CURRENT_DATE as as_of_date
from (select ACB.card_type_code, sum(ACB.breakage) as total_breakage_value
      from (select card.gc_uuid,
                   card.gc_type_code                 as card_type_code,
                   P.tx_value                        as purchase_value,
                   R.total_redeemed                  as redeemed_value,
                   (purchase_value - redeemed_value) as breakage
            from (select gc_uuid, tx_value from transaction where tx_type = 'PURCHASE') P, -- Purchase value on card instance
                 (select gc_uuid, sum(tx_value) as total_redeemed
                  from transaction
                  where tx_type = 'REDEMPTION'
                  group by gc_uuid) R, -- Redeemed value on card instance
                 (select gc_uuid
                  from card
                  where gc_expiry_date < current_date
                    and (CURRENT_DATE::date - gc_expiry_date) < 365) E, -- Expired card instances in this year (YTD)
                 card -- card instances
            where P.gc_uuid = E.gc_uuid
              and E.gc_uuid = card.gc_uuid
              and E.gc_uuid = R.gc_uuid) ACB, -- Breakage in a card instance
           card_type C -- Card type
      where C.gc_type_code = ACB.card_type_code
      group by ACB.card_type_code) BB, -- Total breakage on ALL cards of a particular type
     card_type CC -- Card type
where BB.card_type_code = CC.gc_type_code
order by BB.total_breakage_value desc;
