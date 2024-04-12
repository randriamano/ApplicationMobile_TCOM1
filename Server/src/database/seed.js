const { PrismaClient } = require("@prisma/client");

const prisma = new PrismaClient();

const adminData = [
  {
    adminName: "Admin",
    adminKey: "admin",
  },
];

const productData = [
  {
    productName: "Polo",
    productPrice: "21 000",
    productDescription: "Polo avec les logos TELECOM et ESPA conçu par le GET",
    productCategory: "Vêtements",
    availableColorList: ["Red", "Black", "Navy Blue", "Grey", "White"],
    availableSizeList: ["M", "L", "XL"],
    productImageURLList: ["/images/polo/0", "/images/polo/1", "/images/polo/2", "/images/polo/3"],
    productRemainingStock: 5,
  },
  {
    productName: "Chaussure",
    productPrice: "125 000",
    productDescription: "Chaussure conçu par le GET",
    productCategory: "Vêtements",
    availableColorList: ["Red", "Black", "Navy Blue", "Grey", "White"],
    availableSizeList: ["M", "L", "XL"],
    productImageURLList: [
      "/images/chaussure/0",
      "/images/chaussure/1",
      "/images/chaussure/2",
      "/images/chaussure/3"
    ],
    productRemainingStock: 3,
  },
];

const studentData = [
  {
    studentCardNum: "013-M1",
    studentName: "RANDRIANTSIROFO",
    studentFirstname: "Thiarson Antsa",
    studentPassword: "123456",
  },
  {
    studentCardNum: "011-M1",
    studentName: "RAMAROKOTO",
    studentFirstname: "Tommy",
    studentPassword: "123456",
  },
]

const commandedProduct = [
  {
    productId: 1,
    studentId: 2,
  },
  {
    productId: 2,
    studentId: 1,
  },
]

async function main() {
  console.log(`Start seeding ...`);

  for (const data of adminData) {
    const admin = await prisma.admin.create({
      data: data,
    });
    console.log(`Created admin with key: ${admin.adminKey}`);
  }

  for (const data of productData) {
    const product = await prisma.product.create({
      data: data,
    });
    console.log(`Created product with name: ${product.productName}`);
  }

  for (const data of studentData) {
    const student = await prisma.student.create({
      data: data,
    });
    console.log(`Created student with card number: ${student.studentCardNum}`);
  }

  for (const data of commandedProduct) {
    const command = await prisma.command.create({
      data: data,
    });
    console.log(`Created command`);
  }

  console.log(`Seeding finished.`);
}

main()
  .then(async () => {
    await prisma.$disconnect();
  })
  .catch(async (e) => {
    console.error(e);
    await prisma.$disconnect();
    process.exit(1);
  });
