-- Create Database
use blautech
-- Create User
db.createUser({ user:"testUser",pwd: "testUser",roles:[]})
-- Grant roles to user
db.grantRolesToUser(
   "testUser",
   [ { role: "readWrite", db: "blautech" } ]
)