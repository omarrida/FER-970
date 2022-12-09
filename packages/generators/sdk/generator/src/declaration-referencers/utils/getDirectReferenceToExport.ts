import { GetReferenceOpts, Reference } from "@fern-typescript/contexts";
import { SourceFile, ts } from "ts-morph";
import { convertExportedFilePathToFilePath, ExportedFilePath } from "../../exports-manager/ExportedFilePath";
import { ImportsManager } from "../../imports-manager/ImportsManager";
import { getRelativePathAsModuleSpecifierTo } from "../../utils/getRelativePathAsModuleSpecifierTo";

export function getDirectReferenceToExport({
    exportedName,
    exportedFromPath,
    importsManager,
    referencedIn,
    importAlias,
    subImport = [],
    packageName,
}: {
    exportedName: string;
    exportedFromPath: ExportedFilePath;
    importsManager: ImportsManager;
    referencedIn: SourceFile;
    importAlias: string | undefined;
    subImport?: string[];
    packageName: string;
}): Reference {
    const moduleSpecifier = getRelativePathAsModuleSpecifierTo({
        from: referencedIn,
        to: convertExportedFilePathToFilePath(exportedFromPath),
        packageName,
    });

    const addImport = () => {
        importsManager.addImport(moduleSpecifier, {
            namedImports: [
                {
                    name: exportedName,
                    alias: importAlias,
                },
            ],
        });
    };

    const importedName = importAlias ?? exportedName;

    const entityName = subImport.reduce<ts.EntityName>(
        (acc, subImport) => ts.factory.createQualifiedName(acc, subImport),
        ts.factory.createIdentifier(importedName)
    );

    return {
        getTypeNode: ({ isForComment = false }: GetReferenceOpts = {}) => {
            if (!isForComment) {
                addImport();
            }
            return ts.factory.createTypeReferenceNode(entityName);
        },
        getEntityName: ({ isForComment = false }: GetReferenceOpts = {}) => {
            if (!isForComment) {
                addImport();
            }
            return entityName;
        },
        getExpression: ({ isForComment = false }: GetReferenceOpts = {}) => {
            if (!isForComment) {
                addImport();
            }
            return ts.factory.createIdentifier(importedName);
        },
    };
}
