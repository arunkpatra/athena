-- Yearly breakage rate calculation
-- Description: Given an as-of-date, calculates breakage rate for the prior year by card type.
-- breakage rate for a card is defined as, breakage_rate = (total breakage across ALL cards) / sold value of All cards * 100

-- input, as-of-date in YYYY-MM-DD, e.g. 2020-01-01.
-- Although this is ideally run as an yearly job, it may be run quarterly also to calculate quarterly cut off breakage rates.


select CC.gc_name,
       CC.gc_type_code                                              card_type_code,
       BB.total_purchase_value                                   as ytd_purchase_value,
       BB.total_breakage_value                                   as ytd_breakage,
       (BB.total_breakage_value / BB.total_purchase_value) * 100 as breakage_rate,
       (date_part('year', '2020-07-28') - 1)                     as as_of_year
from (select ACB.card_type_code,
             sum(ACB.purchase_value) as total_purchase_value,
             sum(ACB.breakage)       as total_breakage_value
      from (select card.gc_uuid,
                   card.gc_type_code                 as card_type_code,
                   P.tx_value                        as purchase_value,
                   R.total_redeemed                  as redeemed_value,
                   (purchase_value - redeemed_value) as breakage
            from (select gc_uuid, tx_value from transaction where tx_type = 'PURCHASE') P, -- Purchase value on card instance
                 (select gc_uuid, sum(tx_value) as total_redeemed
                  from transaction
                  where tx_type = 'REDEMPTION'
                  group by gc_uuid) R,                                                     -- Redeemed value on card instance
                 (select gc_uuid, date_part('year', gc_expiry_date) as expiry_year
                  from card
                  where date_part('year', '2020-07-28') =
                        (date_part('year', gc_expiry_date) + 1)) E,                        -- Expired card instances in this year (YTD)
                 card                                                                      -- card instances
            where P.gc_uuid = E.gc_uuid
              and E.gc_uuid = card.gc_uuid
              and E.gc_uuid = R.gc_uuid) ACB, -- Breakage in a card instance
           card_type C                        -- Card type
      where C.gc_type_code = ACB.card_type_code
      group by ACB.card_type_code) BB, -- Total breakage on ALL cards of a particular type
     card_type CC                      -- Card type
where BB.card_type_code = CC.gc_type_code
order by BB.total_breakage_value desc;
