-- CreateTable
CREATE TABLE "Goodies" (
    "id" SERIAL NOT NULL,
    "name" TEXT NOT NULL,
    "price" TEXT NOT NULL,

    CONSTRAINT "Goodies_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Buy" (
    "id" SERIAL NOT NULL,
    "idGoodies" INTEGER NOT NULL,
    "idClient" INTEGER NOT NULL,

    CONSTRAINT "Buy_pkey" PRIMARY KEY ("id")
);

-- AddForeignKey
ALTER TABLE "Buy" ADD CONSTRAINT "Buy_idGoodies_fkey" FOREIGN KEY ("idGoodies") REFERENCES "Goodies"("id") ON DELETE RESTRICT ON UPDATE CASCADE;
