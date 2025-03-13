CREATE TABLE IF NOT EXISTS sessions (
    id uuid NOT NULL PRIMARY KEY,
    user_id bigint references users(id),
    ExpireAt timestamp not null
);