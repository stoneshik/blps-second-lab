DROP TABLE IF EXISTS
    refreshtoken,
    roles,
    tax_features,
    tax_regime,
    taxpayer_category,
    user_roles,
    users
    CASCADE;
DROP SEQUENCE IF EXISTS
    roles_seq,
    users_seq,
    refreshtoken_seq,
    tax_features_seq,
    tax_regime_seq,
    taxpayer_category_seq
    ;