const { PrismaClient } = require('@prisma/client')

const dotenv = require('dotenv')

dotenv.config()

let prisma

const prismaClientSingleton = () => {
  const DATABASE_URL = process.env.DATABASE_URL;

  if (!DATABASE_URL) {
    throw new Error("Database URL required")
  }

  const databaseUrl = new URL(DATABASE_URL)

  if(!prisma) {
    return new PrismaClient({
      datasources: {
        db: {
          url: databaseUrl.toString(),
        },
      },
    })
  } 

  return prisma
}

prisma  = globalThis.prisma ?? prismaClientSingleton()

module.exports = prisma

if (process.env.NODE_ENV !== 'production') globalThis.prisma = prisma
