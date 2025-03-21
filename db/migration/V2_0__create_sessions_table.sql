CREATE TABLE IF NOT EXISTS customSessions (
    id uuid NOT NULL PRIMARY KEY,
    user_id bigint references users(id),
    ExpireAt timestamp not null
);