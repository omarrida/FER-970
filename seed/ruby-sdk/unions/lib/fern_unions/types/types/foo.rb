# frozen_string_literal: true

require "json"

module SeedUnionsClient
  class Types
    class Foo
      attr_reader :name, :additional_properties

      # @param name [String]
      # @param additional_properties [OpenStruct] Additional properties unmapped to the current class definition
      # @return [Types::Foo]
      def initialize(name:, additional_properties: nil)
        # @type [String]
        @name = name
        # @type [OpenStruct] Additional properties unmapped to the current class definition
        @additional_properties = additional_properties
      end

      # Deserialize a JSON object to an instance of Foo
      #
      # @param json_object [JSON]
      # @return [Types::Foo]
      def self.from_json(json_object:)
        struct = JSON.parse(json_object, object_class: OpenStruct)
        JSON.parse(json_object)
        name = struct.name
        new(name: name, additional_properties: struct)
      end

      # Serialize an instance of Foo to a JSON object
      #
      # @return [JSON]
      def to_json(*_args)
        { "name": @name }.to_json
      end

      # Leveraged for Union-type generation, validate_raw attempts to parse the given hash and check each fields type against the current object's property definitions.
      #
      # @param obj [Object]
      # @return [Void]
      def self.validate_raw(obj:)
        obj.name.is_a?(String) != false || raise("Passed value for field obj.name is not the expected type, validation failed.")
      end
    end
  end
end
