const { PrismaClient } = require('@prisma/client')

const prisma = new PrismaClient()

const adminData = [
  {
    name: 'Admin',
    key: 'admin',
  },
]

async function main() {
  console.log(`Start seeding ...`)
  for (const data of adminData) {
    const admin = await prisma.admin.create({
      data: data,
    })
    console.log(`Created admin with key: ${admin.key}`)
  }
  console.log(`Seeding finished.`)
}

main()
  .then(async () => {
    await prisma.$disconnect()
  })
  .catch(async (e) => {
    console.error(e)
    await prisma.$disconnect()
    process.exit(1)
  })
